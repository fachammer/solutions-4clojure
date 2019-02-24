(ns solutions-4clojure.038-maximum-value)

(defn my-max [& coll]
  (reduce (fn [acc n] (if (< acc n) n acc)) coll))
