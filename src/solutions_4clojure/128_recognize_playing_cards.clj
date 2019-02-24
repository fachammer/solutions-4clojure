(ns solutions-4clojure.128-recognize-playing-cards)

(defn suit-rank [[suit-string rank-string]]
  (let [suit {\S :space \H :heart \C :club \D :diamond}
        rank {\2 0 \3 1 \4 2 \5 3 \6 4 \7 5 \8 6 \9 7 \T 8 \J 9 \Q 10 \K 11 \A 12}]
    {:suit (suit suit-string) :rank (rank rank-string)}))
