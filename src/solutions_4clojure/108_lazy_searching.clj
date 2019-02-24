(ns solutions-4clojure.108-lazy-searching)

(defn smallest-common-element [& colls]
  (let [intersection (fn intersection [a & sets]
                       (if (empty? sets)
                         a
                         (apply intersection
                                (set (filter (set a) (first sets)))
                                (rest sets))))]
    (->> [(repeat (count colls) #{}) colls]
         (iterate (fn [[sets remaining-colls]]
                    (let [[min-index min-coll] (apply min-key (comp first second)
                                                      (map-indexed vector remaining-colls))]
                      [(map-indexed (fn [i s] (if (= i min-index)
                                                (conj s (first min-coll))
                                                s)) sets)
                       (map-indexed (fn [i c] (if (= i min-index)
                                                (rest c)
                                                c)) remaining-colls)])))
         (map (comp (partial apply intersection) first))
         (remove empty?)
         first
         first)))
