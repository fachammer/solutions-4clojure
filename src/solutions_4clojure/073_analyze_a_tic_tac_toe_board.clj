(ns solutions-4clojure.073-analyze-a-tic-tac-toe-board)

(defn tic-tac-toe-winner [board]
  (let [rows board
        cols (apply map vector board)
        main-diag (map #(get-in board [% %]) (range 3))
        side-diag (map #(get-in board [% (- 2 %)]) (range 3))
        seqs-to-check (concat rows cols [main-diag] [side-diag])]
    (cond
      (some (partial = [:o :o :o]) seqs-to-check) :o
      (some (partial = [:x :x :x]) seqs-to-check) :x)))
