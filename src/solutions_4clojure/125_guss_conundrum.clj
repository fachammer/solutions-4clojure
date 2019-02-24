(ns solutions-4clojure.125-guss-conundrum)

(fn [] (let [q (char 34)
             p (char 41)
             c (vec "(fn [] (let [q (char 34) p (char 41) c (vec )] (apply str (concat (subvec c 0 44) [q (apply str c) q p] (subvec c 45)))))")]
         (apply str (concat (subvec c 0 44) [q (apply str c) q p] (subvec c 45)))))
