(ns solutions-4clojure.121-universal-computation-engine)

(defn compute [expression]
  (let [op-symbols {(quote +) + (quote -) - (quote *) * (quote /) /}]
    (cond
      (integer? expression) (fn [symbol-map] expression)
      (symbol? expression) (fn [symbol-map] (symbol-map expression))
      :else (let [[f & args] expression]
              (fn [symbol-map]
                (apply (op-symbols f) (map #((compute %) symbol-map) args)))))))
