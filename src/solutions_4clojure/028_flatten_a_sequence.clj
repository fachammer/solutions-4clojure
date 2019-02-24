(ns solutions-4clojure.028-flatten-a-sequence)

(defn my-flatten [coll]
  (if (coll? coll)
    (apply concat (map my-flatten coll))
    [coll]))
