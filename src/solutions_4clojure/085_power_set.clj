(ns solutions-4clojure.085-power-set)

(defn power-set [s]
  (reduce (fn [acc el]
            (apply conj acc (map #(conj % el) acc)))
          #{#{}}
          s))
