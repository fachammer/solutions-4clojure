(ns solutions-4clojure.144-oscilrate)

(defn oscilrate [x & fns]
  (->> [x (cycle fns)]
       (iterate (fn [[val remaining-fns]]
                  [((first remaining-fns) val)
                   (rest remaining-fns)]))
       (map first)))
