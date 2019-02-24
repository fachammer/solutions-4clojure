(ns solutions-4clojure.140-veitch-please)

(defn min-boolean-function [boolean-function]
  (let [missing-symbols (fn [s]
                          (->> #{'A 'a 'B 'b 'C 'c 'D 'd}
                               (remove (->> s
                                            (mapcat (fn [x]
                                                      [(symbol (clojure.string/upper-case (name x)))
                                                       (symbol (clojure.string/lower-case (name x)))]))
                                            set))
                               set))
        union (fn union [& sets] (set (apply concat sets)))
        intersection (fn intersection [a & sets]
                       (set (if sets (filter a (apply intersection sets)) a)))
        cartesian-product (fn cartesian-product [a b] (for [i a j b] [i j]))
        sanitize-boolean-function (fn [f]
                                    (->> f
                                         (mapcat (fn [x] (if-let [m (seq (missing-symbols x))]
                                                           (map (partial conj x) m)
                                                           [x])))
                                         (map set)
                                         set))
        grey-order-ab [['a 'b] ['a 'B] ['A 'B] ['A 'b]]
        grey-order-cd [['c 'd] ['c 'D] ['C 'D] ['C 'd]]
        karnaugh-map (->> (cartesian-product grey-order-cd grey-order-ab)
                          (map (comp set (partial apply concat)))
                          (partition 4)
                          (map vec)
                          vec)
        get-in-wrapped (fn [coll indices]
                         (get-in coll (map #(mod % (count coll)) indices)))
        boolean-function (sanitize-boolean-function boolean-function)
        largest-horizontal-block (fn [[row col]]
                                   (->> [4 2 1]
                                        (map (fn [l]
                                               (map #(get-in-wrapped karnaugh-map
                                                                     [row (+ col %)])
                                                    (range l))))
                                        (filter #(every? boolean-function %))
                                        first))
        largest-vertical-block (fn [[row col]]
                                 (->> [4 2 1]
                                      (map (fn [l]
                                             (map #(get-in-wrapped karnaugh-map
                                                                   [(+ row %) col])
                                                  (range l))))
                                      (filter #(every? boolean-function %))
                                      first))
        block2x2 (fn [[row col]]
                   (first (filter #(every? boolean-function %)
                                  [(map (partial get-in-wrapped karnaugh-map)
                                        [[row col] [row (inc col)]
                                         [(inc row) col] [(inc row) (inc col)]])])))
        block2x4 (fn [[row col]]
                   (first (filter #(every? boolean-function %)
                                  [(map (partial get-in-wrapped karnaugh-map)
                                        [[row col] [row (+ 1 col)]
                                         [row (+ 2 col)] [row (+ 3 col)]
                                         [(inc row) col] [(inc row) (+ 1 col)]
                                         [(inc row) (+ 2 col)] [(inc row) (+ 3 col)]])])))
        block4x2 (fn [[row col]]
                   (first (filter #(every? boolean-function %)
                                  [(map (partial get-in-wrapped karnaugh-map)
                                        [[row col] [row (inc col)]
                                         [(+ 1 row) col] [(+ 1 row) (inc col)]
                                         [(+ 2 row) col] [(+ 2 row) (inc col)]
                                         [(+ 3 row) col] [(+ 3 row) (inc col)]])])))
        block4x4 (first (filter #(every? boolean-function %) [(apply concat karnaugh-map)]))
        blocks (map set
                    (filter (complement nil?)
                            (concat (map largest-horizontal-block
                                         (cartesian-product (range (count karnaugh-map))
                                                            (range (count karnaugh-map))))
                                    (map largest-vertical-block
                                         (cartesian-product (range (count karnaugh-map))
                                                            (range (count karnaugh-map))))
                                    (map block2x2
                                         (cartesian-product (range (count karnaugh-map))
                                                            (range (count karnaugh-map))))
                                    (map block2x4 (for [row (range (count karnaugh-map))] [row 0]))
                                    (map block4x2 (for [col (range (count karnaugh-map))] [0 col]))
                                    [block4x4])))
        subset? (fn [a b] (every? b a))]
    (->> blocks
         distinct
         (iterate (fn [blocks]
                    (when-let [possible-removals (seq (filter (fn [block]
                                                                (subset? block
                                                                         (apply union
                                                                                (remove #{block} blocks)))) blocks))]
                      (remove #{(apply min-key count possible-removals)} blocks))))
         (take-while (complement nil?))
         last
         (map (partial apply intersection))
         set)))
