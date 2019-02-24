(ns solutions-4clojure.034-implement-range)

(defn replicate [coll n]
  (apply concat (map (partial repeat n) coll)))
