(ns solutions-4clojure.090-cartesian-product)

(defn cartesian-product [a b]
  (set (for [i a
             j b]
         [i j])))
