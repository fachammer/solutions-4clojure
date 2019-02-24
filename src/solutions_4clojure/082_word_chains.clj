(ns solutions-4clojure.082-word-chains)

(defn word-chainable? [words]
  (letfn [(subsequence [a b]
            (when-let [result (->> (reductions (fn [[indices remaining] next-value]
                                                 (let [i (.indexOf remaining next-value)]
                                                   (if (= i -1)
                                                     nil
                                                     [(conj indices (+ (inc i) (last indices)))
                                                      (drop (inc i) remaining)])))
                                               [[-1] (seq b)]
                                               (seq a))
                                   (take-while (complement nil?))
                                   last)]
              (rest (first result))))
          (addition-or-deletion? [a b]
                                 (let [larger (max-key count a b)
                                       smaller (min-key count a b)]
                                   (and (= 1 (- (count larger) (count smaller)))
                                        (subsequence smaller larger))))
          (substitution? [a b]
                         (and (= (count a) (count b))
                              (= 1 (count (filter #(apply not= %) (map vector a b))))))
          (connected? [a b]
                      (and (not= a b) (or (addition-or-deletion? a b) (substitution? a b))))
          (intersection [a b]
                        (set (filter a b)))]
    (let [graph (into {} (map (fn [word] [word (->> words (filter (partial connected? word)) set)]) words))]
      (->> (let [[first-word first-connected-words] (first graph)]
             [[first-word first-connected-words] (disj words first-word) '()])
           (iterate (fn [arg]
                      (if (or (true? arg) (false? arg))
                        arg
                        (let [[[word connected-words] remaining-words stack] arg]
                          (if (empty? remaining-words)
                            true
                            (if-let [next-word (first (intersection connected-words remaining-words))]
                              [[next-word (graph next-word)]
                               (disj remaining-words next-word)
                               (cons [[word (disj connected-words next-word)] remaining-words stack] stack)]
                              (if (empty? stack)
                                false
                                (first stack))))))))

           (drop-while (complement #(or (true? %) (false? %))))
           first))))
