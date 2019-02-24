(ns solutions-4clojure.079-triangle-minimal-path)

(defn minimal-triangle-path [triangle]
  (let [triangle-vec         (vec triangle)
        start-nodes          (map (fn [i] [(dec (count triangle)) i]) (range (count triangle)))
        adjacent-upper-nodes (fn [[row col]] (cond
                                               (= 0 col) [[(dec row) col]]
                                               (= row col) [[(dec row) (dec col)]]
                                               :else [[(dec row) col] [(dec row) (dec col)]]))
        node-value           (partial get-in triangle-vec)
        next-node            (fn [current-node] (apply min-key node-value (adjacent-upper-nodes current-node)))
        path-sum (fn [path] (apply + (map node-value path)))
        min-path-from-node (fn [start-node] (take (count triangle-vec) (iterate next-node start-node)))]
    (->> start-nodes
         (map min-path-from-node)
         (apply min-key path-sum)
         path-sum)))
