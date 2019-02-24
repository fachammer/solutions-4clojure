(ns solutions-4clojure.119-win-at-tic-tac-toe)

(defn immediate-tic-tac-toe-win-placements [piece board]
  (let [tic-tac-toe-winner (fn tic-tac-toe-winner [board]
                             (let [rows board
                                   cols [(map first board) (map second board) (map last board)]
                                   main-diag (map #(get-in board [% %]) (range 3))
                                   side-diag (map #(get-in board [% (- 2 %)]) (range 3))
                                   seqs-to-check (concat rows cols [main-diag] [side-diag])]
                               (cond
                                 (some #(= % [:o :o :o]) seqs-to-check) :o
                                 (some #(= % [:x :x :x]) seqs-to-check) :x
                                 :else nil)))
        empty-positions (filter #(= :e (get-in board %))
                                (for [row (range 3)
                                      col (range 3)]
                                  [row col]))]
    (->> empty-positions
         (filter #(= piece (tic-tac-toe-winner (assoc-in board % piece))))
         set)))
