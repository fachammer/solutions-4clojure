(ns solutions-4clojure.110-sequence-of-pronounciations)

(defn pronunciations [coll]
  (->> coll
       (iterate (fn [c]
                  (->> c
                       (partition-by identity)
                       (mapcat (fn [x] [(count x) (first x)])))))
       (drop 1)))
