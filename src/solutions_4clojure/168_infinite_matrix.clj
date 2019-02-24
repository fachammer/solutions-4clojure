(ns solutions-4clojure.168-infinite-matrix)

(defn infinite-matrix
  ([f] (infinite-matrix f 0 0))
  ([f m n]
   (let [row-fn (fn row-fn [m n]
                  (lazy-seq (cons (f m n) (row-fn m (inc n)))))]
     (lazy-seq (cons (row-fn m n) (infinite-matrix f (inc m) n)))))
  ([f m n s t]
   (take s (map (partial take t) (infinite-matrix f m n)))))
