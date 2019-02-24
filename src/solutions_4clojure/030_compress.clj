(ns solutions-4clojure.030-compress)

(defn compress [coll]
  (map first (partition-by identity coll)))
