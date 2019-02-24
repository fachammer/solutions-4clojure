(ns solutions-4clojure.127-love-triangle)

(defn largest-isoscele [layers]
  (let [binary-digits (fn [n] (->> [[] n]
                                   (iterate (fn [x]
                                              (when x
                                                (let [[digits remainder] x]
                                                  (when-not (= 0 remainder)
                                                    [(cons (mod remainder 2) digits)
                                                     (quot remainder 2)])))))
                                   (take-while (complement nil?))
                                   last
                                   first))
        unequal-cross-section (map binary-digits layers)
        max-digits (apply max (map count unequal-cross-section))
        pad (fn pad [coll] (concat (repeat (- max-digits (count coll)) 0) coll))
        cross-section (vec (map (comp vec pad) unequal-cross-section))
        directed-inclusive-range (fn [start end]
                                   (if (< start end)
                                     (range start (inc end) 1)
                                     (range start (dec end) -1)))
        connection-line (fn connection-line [[from-row from-col] [to-row to-col]]
                          (cond
                            (= from-row to-row)
                            (map vector
                                 (repeat from-row)
                                 (directed-inclusive-range from-col to-col))

                            (= from-col to-col)
                            (map vector
                                 (directed-inclusive-range from-row to-row)
                                 (repeat from-col))

                            (or (= (- to-row from-row) (- to-col from-col))
                                (= (- to-row from-row) (- (- to-col from-col))))
                            (map vector
                                 (directed-inclusive-range from-row to-row)
                                 (directed-inclusive-range from-col to-col))))
        fits-line? (fn fits-line? [line] (not (some (partial contains? #{0 nil})
                                                    (map (partial get-in cross-section) line))))
        orientation-increment-map {:top          (fn [[srow scol] [erow ecol]]
                                                   [[(inc srow) (dec scol)]
                                                    [(inc erow) (inc ecol)]])
                                   :top-right    (fn [[srow scol] [erow ecol]]
                                                   [[srow (dec scol)]
                                                    [(inc erow) ecol]])
                                   :right        (fn [[srow scol] [erow ecol]]
                                                   [[(dec srow) (dec scol)]
                                                    [(dec erow) (inc ecol)]])
                                   :bottom-right (fn [[srow scol] [erow ecol]]
                                                   [[(dec srow) scol]
                                                    [erow (dec ecol)]])
                                   :bottom       (fn [[srow scol] [erow ecol]]
                                                   [[(dec srow) (inc scol)]
                                                    [(dec erow) (dec ecol)]])
                                   :bottom-left  (fn [[srow scol] [erow ecol]]
                                                   [[srow (inc scol)]
                                                    [(dec erow) ecol]])
                                   :left         (fn [[srow scol] [erow ecol]]
                                                   [[(inc srow) (inc scol)]
                                                    [(dec erow) (inc ecol)]])
                                   :top-left     (fn [[srow scol] [erow ecol]]
                                                   [[(inc srow) scol]
                                                    [erow (inc ecol)]])}
        isoscele-size (comp (partial apply +) (partial map count))
        largest-isoscele-oriented (fn [position orientation]
                                    (->> [[position]]
                                         (iterate (fn [isoscele]
                                                    (let [previous-start (first (last isoscele))
                                                          previous-end (last (last isoscele))]
                                                      (->> ((orientation-increment-map orientation)
                                                            previous-start
                                                            previous-end)
                                                           (apply connection-line)
                                                           (conj isoscele)))))
                                         (take-while (comp fits-line? last))
                                         last))
        largest-isoscele-at (fn [position]
                              (apply max-key isoscele-size
                                     (map (partial largest-isoscele-oriented position)
                                          (keys orientation-increment-map))))]
    (let [isoscele (apply max-key isoscele-size
                          (map largest-isoscele-at
                               (for [row (range (count cross-section))
                                     col (range max-digits)]
                                 [row col])))]
      (when (> (isoscele-size isoscele) 1)
        [isoscele (isoscele-size isoscele)]))))
