(ns solutions-4clojure.029-get-the-caps)

(defn compress [coll]
  (map first (partition-by identity coll)))
