(ns solutions-4clojure.106-number-maze)

(defn shortest-number-maze-path [a b]
  (let [connected-nodes (fn [n]
                          (->> (conj #{} (+ 2 n) (* 2 n)
                                     (if (odd? n) (+ n 2) (/ n 2)))
                               (filter (partial >= (+ 2 (* 2 (max a b)))))
                               set))
        [_ node-map] (->> [[a] {a {:cost 0}} #{} false]
                          (iterate (fn [[[node & others :as to-visit] node-map visited-edges finished]]
                                     (when (not finished)
                                       (let [adjacent-nodes (remove (fn [n] (visited-edges [node n]))
                                                                    (connected-nodes node))
                                             adjacent-nodes-properties (->> adjacent-nodes
                                                                            (map (fn [n]
                                                                                   [n (min-key :cost
                                                                                               {:predecessor (:predecessor (node-map n))
                                                                                                :cost (or (:cost (node-map n))
                                                                                                          Integer/MAX_VALUE)}
                                                                                               {:predecessor node
                                                                                                :cost (inc (:cost (node-map node)))})]))
                                                                            (into {}))
                                             nodes-to-visit (concat others adjacent-nodes)]
                                         [nodes-to-visit
                                          (into node-map adjacent-nodes-properties)
                                          (set (concat visited-edges
                                                       (map vector
                                                            (repeat node)
                                                            adjacent-nodes)))
                                          (= 0 (count nodes-to-visit))]))))
                          (drop-while (fn [[_ _ _ finished]] (not finished)))
                          first)]
    (->> b
         (iterate (comp :predecessor node-map))
         (take-while (complement nil?))
         reverse)))
