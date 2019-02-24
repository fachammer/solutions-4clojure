(ns solutions-4clojure.117-for-science)

(defn maze-solvable? [maze]
  (let [find-position (fn [c]
                        (first (for [row (range (count maze))
                                     col (range (count (first maze)))
                                     :when (= (get-in maze [row col]) c)]
                                 [row col])))
        mouse-position (find-position \M)
        cheese-position (find-position \C)
        walkable-positions (fn [[row col]]
                             (->> #{[row (inc col)] [row (dec col)]
                                    [(inc row) col] [(dec row) col]}
                                  (filter (fn [[r c]] (and (>= r 0) (>= c 0)
                                                           (< r (count maze))
                                                           (< c (count (first maze))))))
                                  (remove #(#{\# \M} (get-in maze %)))))]
    (boolean
     ((->> [mouse-position #{} []]
           (iterate (fn [[position visited-positions stack]]
                      (let [[next-position & save] (remove visited-positions
                                                           (walkable-positions position))]
                        (if next-position
                          [next-position
                           (conj visited-positions next-position)
                           (concat stack save)]
                          [(first stack) visited-positions (rest stack)]))))
           (drop-while #(not (nil? (first %))))
           first
           second)
      cheese-position))))
