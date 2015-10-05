(define (problem shakey_prob1)
  (:domain shakey)
  (:objects
    room-1 room-2 room-3
    door-1 door-2 door-3

    lswitch-1 lswitch-2 lswitch-3

    ball box shakey leftArm rightArm
    
    )

  (:init
    (connected room-1 room-2 door-1) (connected room-2 room-1 door-1)
    (connected room-2 room-3 door-2) (connected room-3 room-2 door-2)
    (connected room-2 room-3 door-3) (connected room-3 room-2 door-3)

    (in shakey room-1) (movable shakey)

    (wide door-1)
    (wide door-3)

    (in lswitch-1 room-1)(in lswitch-2 room-2)(in lswitch-3 room-3)

    (in box room-3) (pushable box) (climbable box)
    (in ball room-2) (carriable ball)
    )

  (:goal (and(in ball room-1)
    (in box room-3)
    (in shakey room-1))
    )

  ;(:goal (and (turnedOn lswitch-1)
  ;  (turnedOn lswitch-2)
  ;  (turnedOn lswitch-3)
  ;  (in box room-1)
  ;  (in shakey room-3)))

)
