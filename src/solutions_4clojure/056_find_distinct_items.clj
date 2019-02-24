(ns solutions-4clojure.056-find-distinct-items)

(defn my-distinct [coll]
  (first (reduce (fn [[dups-vec dups-set] next-value] [(if (dups-set next-value)
                                                         dups-vec
                                                         (conj dups-vec next-value))
                                                       (conj dups-set next-value)])
                 [[] #{}]
                 coll)))
