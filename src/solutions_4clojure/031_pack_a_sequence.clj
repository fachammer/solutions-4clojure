(ns solutions-4clojure.031-pack-a-sequence)

(defn pack [coll]
  (partition-by identity coll))
