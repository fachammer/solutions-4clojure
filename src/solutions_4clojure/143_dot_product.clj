(ns solutions-4clojure.143-dot-product)

(defn dot-product [a b]
  (apply + (map * a b)))
