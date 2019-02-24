(ns solutions-4clojure.152-latin-square-slicing)

(defn latin-squares [coll]
  (let [max-count (apply max (map count coll))
        bases (map (fn [v] (- max-count (count v))) coll)
        successor (fn successor [[first-base & more-bases :as bases] [first-current & more-current :as current]]
                    (cond
                      (= bases current) nil
                      (and first-base (= first-base first-current)) (cons 0 (successor more-bases more-current))
                      :else (cons (inc first-current) more-current)))
        alignment-ids (take-while identity (iterate (partial successor bases) (repeat (count bases) 0)))
        alignments (map (fn align [alignment-id]
                          (vec (map-indexed (fn [i v]
                                              (vec (concat (repeat (nth alignment-id i) nil)
                                                           v
                                                           (repeat (- max-count (nth alignment-id i) (count v)) nil))))
                                            coll)))
                        alignment-ids)
        transpose (comp vec #(apply map vector %))
        latin-square? (fn latin-square? [square]
                        (let [size (count square)]
                          (and (= size (count (set (flatten square))))
                               (not (contains? (set (flatten square)) nil))
                               (every? (partial apply distinct?) square)
                               (every? (partial apply distinct?) (transpose square)))))
        candidates (fn candidates [alignment]
                     (let [rows (count alignment)
                           cols (count (first alignment))
                           transposed-alignment (transpose alignment)]
                       (distinct (for [row (range rows)
                                       col (range cols)
                                       :let [alignment-row (alignment row)
                                             value (alignment-row col)]
                                       :when value
                                       :let [alignment-col (transposed-alignment col)
                                             max-col-size (dec (or (first (remove #(or (not (alignment-row (+ col %)))
                                                                                       (apply distinct? (subvec alignment-row col (+ col %)))) (range 2 (- cols col))))
                                                                   (inc (- cols col))))
                                             max-row-size (dec (or (first (remove #(or (not (alignment-col (+ row %)))
                                                                                       (apply distinct? (subvec alignment-col row (+ row %)))) (range 2 (- rows row))))
                                                                   (inc (- rows row))))
                                             sizes-to-check (filter #(= (count (clojure.set/union (set (subvec alignment-row col (+ col %)))
                                                                                                  (set (subvec alignment-col row (+ row %)))))
                                                                        %)
                                                                    (range 2 (inc (min max-col-size max-row-size))))]
                                       size sizes-to-check]
                                   (vec (map #(subvec (alignment (+ row %)) col (+ col size)) (range size)))))))]
    (->> alignments
         (mapcat candidates)
         distinct
         (filter latin-square?)
         (map count)
         frequencies)))
