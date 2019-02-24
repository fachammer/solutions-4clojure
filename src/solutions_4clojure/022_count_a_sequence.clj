(ns solutions-4clojure.022-count-a-sequence)

(defn my-count [coll]
  (apply + (map (fn [_] 1) coll)))
