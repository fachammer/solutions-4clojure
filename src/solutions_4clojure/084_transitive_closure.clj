(ns solutions-4clojure.084-transitive-closure)

(defn transitive-closure [relation]
  (let [traverse-depth-first (fn [graph start-node]
                               (->> [(list start-node) #{} []]
                                    (iterate (fn [[to-visit visited path]]
                                               (let [not-visited (filter (complement visited) to-visit)]
                                                 (if-let [next-to-visit (first not-visited)]
                                                   [(concat (graph next-to-visit)
                                                            (rest not-visited))
                                                    (conj visited next-to-visit)
                                                    (conj path next-to-visit)]
                                                   [nil visited path]))))
                                    (drop-while (fn [[to-visit]] (not (nil? to-visit))))
                                    first
                                    last))
        graph (into {} (map (fn [[k v]] [k (map second v)]) (group-by first relation)))
        closed-graph (->> (keys graph)
                          (map (fn [start-node]
                                 [start-node (rest (traverse-depth-first graph start-node))]))
                          (into {}))]
    (->> closed-graph
         (map (fn [[k v]] (map (fn [x] [k x]) v)))
         (apply concat)
         set)))
