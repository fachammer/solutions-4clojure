(ns solutions-4clojure.098-equivalence-classes)

(defn equivalence-classes [f domain]
  (->> domain
       (group-by f)
       vals
       (map set)
       set))
