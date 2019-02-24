(ns solutions-4clojure.103-generating-k-combinations)

(defn combinations [k coll]
  (let [increasing-ranges (fn increasing-ranges [n k]
                            (if (= 1 k)
                              (map list (range n))
                              (apply concat
                                     (map (fn [i]
                                            (map (comp (partial cons i)
                                                       (partial map (partial + i 1)))
                                                 (increasing-ranges (- n i 1) (dec k))))
                                          (range n)))))
        projections (fn [coll n]
                      (map (partial map (partial nth coll))
                           (increasing-ranges (count coll) n)))]
    (->> k
         (projections (seq coll))
         (map set)
         set)))
