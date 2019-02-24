(ns solutions-4clojure.113-making-data-dance)

(defn list-proxy [& args]
  (reify
    Object
    (toString [this] (apply str (interpose ", " (sort args))))
    clojure.lang.Seqable
    (seq [this] (seq (distinct args)))))
