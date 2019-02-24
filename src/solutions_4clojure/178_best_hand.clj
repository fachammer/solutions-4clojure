(ns solutions-4clojure.178-best-hand)

(defn best-poker-hand [hand]
  (let [suit-rank (fn suit-rank [[suit-string rank-string]]
                    (let [suit {\S :space \H :heart \C :club \D :diamond}
                          rank {\2 0 \3 1 \4 2 \5 3 \6 4 \7 5 \8 6 \9 7 \T 8 \J 9 \Q 10 \K 11 \A 12}]
                      {:suit (suit suit-string) :rank (rank rank-string)}))
        parsed-hand (set (map suit-rank hand))
        rank-groups (group-by :rank parsed-hand)
        suit-groups (group-by :suit parsed-hand)
        in-sequence? (or
                      (= (set (keys rank-groups)) #{12 0 1 2 3})
                      (every? (fn [[{rank-a :rank} {rank-b :rank}]]
                                (= 1 (- rank-b rank-a)))
                              (partition 2 1 (sort-by :rank parsed-hand))))]
    (cond
      (and in-sequence? (= 1 (count suit-groups))) :straight-flush
      (some (fn [[_ group]] (= 4 (count group))) rank-groups) :four-of-a-kind
      (and (some (fn [[_ group]] (= 3 (count group))) rank-groups)
           (some (fn [[_ group]] (= 2 (count group))) rank-groups)) :full-house
      (= 1 (count suit-groups)) :flush
      in-sequence? :straight
      (some (fn [[_ group]] (= 3 (count group))) rank-groups) :three-of-a-kind
      (= 2 (count (filter (fn [[_ group]] (= 2 (count group))) rank-groups))) :two-pair
      (some (fn [[_ group]] (= 2 (count group))) rank-groups) :pair
      :else :high-card)))
