(ns solutions-4clojure.097-pascals-triangle)

(defn pascals-triangle-row [n]
  (if (= 1 n)
    [1]
    (->> (concat [0] (pascals-triangle-row (dec n)) [0])
         (partition 2 1)
         (map (partial apply +))
         vec)))
