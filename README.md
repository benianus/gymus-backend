# Gymus

## Description:

This is a gym management system I’m working on, actually. I already know the gym business model,
having trained in it for a while, so I decided to digitize it and solve the problem. I’m already
done with gathering requirements, analyzing them, & create the database design. The next step will
be the implementation.

### Problem:

Everything is on paper, so the risk of missing data is increased.

There are no clear statistics.

## Project brief requirements:

The gym has 3 users: the owner, 2 employees for the moment, and members. A member can train in the
gym in 2 ways:

#### - per session:

1. body building 100 dzd.
2. cardio 50 dzd. Boyd building + cardio 150 dzd.

#### - per month membership:

1. body building 1200 dzd + 10 mins cardio.
2. unlimited cardio time 700 dzd.
3. Bodybuild + unlimited cardio1900 dzd.

If a member wants to subscribe, he should bring his birth certificate a medical certificate, and an
image.

If a member is under 18 years old, they should bring a parental authorization.

The owner will produce a gym card for a subscribed member. The employee can renew members'
subscriptions and record their attendance.

A subscribed member can have just one attendance per day.

The gym has a store where members can buy water, energetic drinks, gym accessories.. Etc. The store
products added by the gym owner.

Every sale should be registered by the employee who is responsible for selling it.

## Functional Requirements:

### Users:

- [x] Login
- [x] Register

### Members:

- [x] Register Memberships
- [x] Record attendances
- [x] Renew memberships
- [x] Get all members
- [x] Get member card
- [ ] Update Member
- [ ] Delete Member

### Sessions:

- [x] Get all sessions
- [x] Register a session

### Store:

- [x] Get all products
- [x] Get product
- [x] Add new product
- [x] Register new sale
- [ ] Update product
- [ ] Delete product

### Reports:

#### Sales:

1. [x] Total sales (memberships, sessions & store)
2. [x] Monthly sales (memberships, sessions & store)
3. [x] Total store sales
4. [x] Monthly store sales
5. [x] Total sessions sales
6. [x] Monthly sessions sales
7. [x] Total memberships sales
8. [x] Total active memberships sales
9. [x] Monthly memberships sales
10. [x] Monthly active memberships sales

#### Revenue:

1. [x] Total revenue (memberships, sessions & store)
2. [x] Monthly revenue (memberships, sessions & store)
3. [x] Total store revenue
4. [x] Monthly store revenue
5. [x] Total sessions revenue
6. [x] Monthly sessions revenue
7. [x] Total memberships revenue
8. [x] Monthly memberships revenue
9. [x] Total active memberships revenue
10. [x] Monthly active memberships revenue

#### Range Statistics:

1. [ ] FromMonthToMonth
2. [ ] FromDayToDay
3. [ ] FromYearToYear

## Non-Functional Requirements:

#### Security:

1. [ ] HTTPS
2. [X] CORS
3. [x] JWT authentication
4. [x] Role-based authorization
5. [x] Ownership access policies
6. [ ] Refresh token & logout
7. [ ] Rate limiting
8. [ ] Logging & Auditing

#### Testing: