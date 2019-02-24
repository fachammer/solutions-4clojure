(ns solutions-4clojure.094-game-of-life)

(defn game-of-life-step [[first-row :as board]]
  (let [neighbours (fn [[row col]]
                     (set (for [i (range (dec row) (+ 2 row))
                                j (range (dec col) (+ 2 col))
                                :when (and (not= [i j] [row col])
                                           (>= row 0)
                                           (>= col 0)
                                           (< row (count board))
                                           (< col (count first-row)))]
                            [i j])))
        alive-now? (fn [alive-state] (= alive-state \#))
        alive-state (fn [[row col]] (get-in board [row col]))
        alive-in-next-state? (fn [cell-alive neighbours]
                               (#{[true 2] [true 3] [false 3]}
                                [cell-alive (count (filter identity neighbours))]))
        coordinates (for [row (range (count board))
                          col (range (count first-row))]
                      [row col])
        alive-symbol (fn [alive-state?] (if alive-state? \# \space))]
    (->> coordinates
         (map (->> neighbours
                   (comp (partial map (comp alive-now? alive-state)))
                   (juxt (comp alive-now? alive-state))
                   (comp alive-symbol (partial apply alive-in-next-state?))))
         (partition (count first-row))
         (map (partial apply str)))))
