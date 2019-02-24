(ns solutions-4clojure.146-trees-into-tables)

(defn flatten-map [m]
  (into {} (for [[k1 m1] m
                 [k2 v] m1]
             [[k1 k2] v])))
