(ns solutions-4clojure.019-last-element)

(defn last-element [coll]
  (first (reverse coll)))
