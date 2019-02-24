(ns solutions-4clojure.111-crossword-puzzle)

(defn placeable-on-crossword? [word crossword]
  (let [sanitized-board (map #(clojure.string/replace % " " "") crossword)
        vertical-board (map (fn [i] (apply str (map #(nth % i) sanitized-board)))
                            (range (count (first sanitized-board))))
        horizontal-spots (remove empty? (map #(clojure.string/split % #"#")
                                             sanitized-board))
        vertical-spots (remove empty? (map #(clojure.string/split % #"#")
                                           vertical-board))
        spot-regexes (map #(-> % (clojure.string/replace "_" ".") re-pattern)
                          (apply concat (concat horizontal-spots
                                                vertical-spots)))]
    (boolean (some #(re-matches % word) spot-regexes))))
