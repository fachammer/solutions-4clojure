(ns solutions-4clojure.177-balanced-brackets)

(defn balanced-brackets? [s]
  (let [bracket-map {\{ \} \[ \] \( \)}
        balance-result (reductions (fn [bracket-stack arg]
                                     (case arg
                                       (\{ \[ \() (cons arg bracket-stack)
                                       (\} \] \)) (and (= arg (bracket-map
                                                               (first bracket-stack)))
                                                       (rest bracket-stack))
                                       bracket-stack))
                                   '()
                                   s)]
    (and (not (some false? balance-result))
         (empty? (last balance-result)))))
