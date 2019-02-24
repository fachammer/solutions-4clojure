(ns solutions-4clojure.091-graph-connectivity)

(defn connected? [graph]
  (let [graph-map (->> graph
                       (map (comp vec reverse))
                       (concat graph)
                       (group-by first)
                       (map (fn [[k v]] [k (set (map second v))]))
                       (into {}))
        traverse-depth-first (fn [graph start-node]
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
                                    last))]
    (= (set (traverse-depth-first graph-map (first (keys graph-map))))
       (set (keys graph-map)))))
