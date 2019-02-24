(ns solutions-4clojure.059-juxtaposition)

(defn my-juxt [& functions]
  (fn [& args]
    (map #(apply % args) functions)))
