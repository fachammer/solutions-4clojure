(ns solutions-4clojure.021-nth-element)

(defn nth-element [coll n]
  (if (= n 0)
    (first coll)
    (recur (rest coll) (dec n))))
