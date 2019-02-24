(ns solutions-4clojure.150-palindromic-numbers)

(defn palindromes-bigger-or-equal-to [n]
  (let [palindrome? (fn palindrome? [x] (= (seq (str x)) (reverse (str x))))
        next-palindrome (fn next-palindrome [x]
                          (let [s (str x)]
                            (if (every? #(= \9 %) s)
                              (+' 2 x)
                              (let [half-including-mid (apply str (take (quot (inc (count s)) 2)
                                                                        s))
                                    next-half-including-mid (inc (bigint half-including-mid))
                                    mirror (bigint (apply str
                                                          half-including-mid
                                                          (->> (str s)
                                                               (take (quot (count s) 2))
                                                               reverse)))]
                                (if (< x mirror)
                                  mirror
                                  (bigint (apply str
                                                 next-half-including-mid
                                                 (reverse (take (quot (count s) 2)
                                                                (str next-half-including-mid))))))))))]

    (iterate next-palindrome (if (palindrome? n) n (next-palindrome n)))))
