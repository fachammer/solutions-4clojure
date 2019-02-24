(ns solutions-4clojure.089-graph-tour)

(defn euler-graph? [graph]
  (let [graph-without-loops (filter (partial apply not=) graph)]
    (if (empty? graph-without-loops)
      false
      (let [graph-with-both-edges (->> graph-without-loops
                                       (map (comp vec reverse))
                                       (concat graph-without-loops))
            zero-degrees (->> graph-with-both-edges
                              (map (fn [[k v]] [k 0]))
                              (into {}))
            degrees (->> graph-with-both-edges
                         (map (partial apply hash-map))
                         (apply merge-with (fn [old new] (inc old)) zero-degrees))
            odd-parity-groups (group-by (comp odd? degrees) (keys degrees))]
        (case (count (odd-parity-groups true))
          (0 2) true
          false)))))
