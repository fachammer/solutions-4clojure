(ns solutions-4clojure.124-analyze-reversi)

(fn reversi-moves [board color]
  (let [coordinates (for [row (range 4) col (range 4)] [row col])
        pieces-of-same-color (filter #(= (get-in board %) color) coordinates)
        directed-inclusive-range (fn [start end]
                                   (if (< start end)
                                     (range start (inc end) 1)
                                     (range start (dec end) -1)))
        connection (fn [[from-row from-col] [to-row to-col]]
                     (cond
                       (= from-row to-row)
                       (map vector (repeat from-row) (directed-inclusive-range from-col to-col))

                       (= from-col to-col)
                       (map vector (directed-inclusive-range from-row to-row) (repeat from-col))

                       (or (= (- to-row from-row) (- to-col from-col))
                           (= (- to-row from-row) (- (- to-col from-col))))
                       (map vector (directed-inclusive-range from-row to-row) (directed-inclusive-range from-col to-col))))
        flipped-disks (fn [coord]
                        (when (= 'e (get-in board coord))
                          (set (apply concat (map #(->> % (drop 1) drop-last)
                                                  (remove (fn [path] (or (nil? path)
                                                                         (>= 2 (count path))
                                                                         (some (fn [c] (#{'e color} (get-in board c)))
                                                                               (drop 1 (drop-last path)))))
                                                          (map (partial connection coord) pieces-of-same-color)))))))]
    (into {} (remove (fn [[_ conn]] (or (nil? conn) (empty? conn))) (map vector coordinates (map flipped-disks coordinates))))))
