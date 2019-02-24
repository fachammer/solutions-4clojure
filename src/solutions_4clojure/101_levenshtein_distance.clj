(ns solutions-4clojure.101-levenshtein-distance)

(defn levenshtein-distance [a b]
  (let [increasing-ranges (fn increasing-ranges [n k]
                            (if (= 1 k)
                              (map list (range n))
                              (->> (range n)
                                   (map (fn [i]
                                          (->> (increasing-ranges (- n i 1) (dec k))
                                               (map (comp (partial cons i)
                                                          (partial map (partial + i 1)))))))

                                   (apply concat))))
        projections (fn [coll n]
                      (map (partial map (partial nth coll))
                           (increasing-ranges (count coll) n)))
        max-coll (max-key count a b)
        min-coll (if (= max-coll a) b a)
        distance (fn [c d]
                   (->> (map vector c d)
                        (filter (partial apply not=))
                        count))
        distances (map distance
                       (projections max-coll (count min-coll))
                       (repeat min-coll))]
    (+ (- (count max-coll) (count min-coll))
       (apply min (if-not (empty? distances) distances [0])))))
