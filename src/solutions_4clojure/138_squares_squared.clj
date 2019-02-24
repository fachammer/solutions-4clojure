(ns solutions-4clojure.138-squares-squared)

(defn squares-in-a-square [base limit]
  (let [squares (take-while (partial >= limit) (iterate #(* % %) base))
        squares-string (apply str squares)
        next-square (first (filter (partial <= (count squares-string))
                                   (map #(* % %) (range))))
        spiral (reductions (fn [[row col] [row-dir col-dir]]
                             [(+ row row-dir) (+ col col-dir)])
                           [0 0]
                           (mapcat identity
                                   (map repeat
                                        (mapcat (partial repeat 2) (drop 1 (range)))
                                        (cycle [[1 1] [1 -1] [-1 -1] [-1 1]]))))
        padded-string (apply str squares-string
                             (repeat (- next-square (count squares-string)) "*"))
        string-square-size (dec (* 2 (int (Math/sqrt next-square))))
        image-map (into {} (map vector spiral padded-string))
        min-row (apply min (map first (keys image-map)))
        max-row (apply max (map first (keys image-map)))
        min-col (apply min (map second (keys image-map)))
        max-col (apply max (map second (keys image-map)))
        normalized-image-map (into {} (map (fn [[[row col] v]]
                                             [[(- row min-row)
                                               (- col min-col)] v]) image-map))]
    (map (partial apply str)
         (partition (inc (- max-col min-col))
                    (map (fn [[row col]] (or (normalized-image-map [row col]) " "))
                         (for [row (range (inc (- max-row min-row)))
                               col (range (inc (- max-col min-col)))]
                           [row col]))))))
