(ns solutions-4clojure.153-pairwise-disjoint-sets)

(defn mutually-disjoint? [sets]
  (let [intersection (fn intersection [a & sets]
                       (if-not (seq sets)
                         a
                         (filter (partial contains? a) (apply intersection sets))))]
    (every? identity (map (fn [s]
                            (every? #(empty? (intersection s %))
                                    (remove #{s} sets)))
                          sets))))
