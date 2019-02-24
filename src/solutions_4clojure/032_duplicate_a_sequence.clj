(ns solutions-4clojure.032-duplicate-a-sequence)

(defn duplicate [coll]
  (apply concat (map (partial repeat 2) coll)))
