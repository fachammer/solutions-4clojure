(ns solutions-4clojure.050-split-by-type)

(defn type-groups [coll]
  (vals (group-by type coll)))
