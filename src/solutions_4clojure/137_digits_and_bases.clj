(ns solutions-4clojure.137-digits-and-bases)

(defn ->base [n b]
  (->> [[] n]
       (iterate (fn [[result remainder]]
                  (when-not (= remainder 0)
                    [(conj result (mod remainder b)) (quot remainder b)])))
       (take-while (complement nil?))
       last
       first
       reverse
       ((fn [x] (if (empty? x) [0] x)))))
