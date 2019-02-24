(ns solutions-4clojure.147-pascals-trapezoid)

(defn pascal-trapzeoid [start-coll]
  (iterate (fn [coll]
             (map (partial apply +')
                  (partition 2 1 (concat [0] coll [0]))))
           start-coll))
