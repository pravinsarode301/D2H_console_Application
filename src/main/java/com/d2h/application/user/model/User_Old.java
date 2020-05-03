package com.d2h.application.user.model;
///**
// * 
// */
//package com.verito.assignment.model;
//
///**
// * @author pravt
// *
// */
//
//// builder patter for fluent api
//public interface User_Old extends Cloneable{
//    String getFirstName();
//    String getLastName();
//    String getPhoneNumber();
//    Integer getBalance();
//	String getUserName();
//	String getPassword();
//    String getAddress();
//
//    
//    
//    class DefaultUser implements User_Old{
//
//        private  String userName;
//        private  String password;
//
//        protected String phoneNumber;
//        protected String address;
//        protected static int balance=100;
//        protected  String firstName;
//        protected  String lastName;
//        
//        public DefaultUser(String userName, String password) {
//			this.userName = userName;
//            this.password = password;
//        }
//        
//        protected DefaultUser(Builder builder) {
//			this(builder.getUserName(), builder.getPassword());
//            this.phoneNumber = builder.phoneNumber;
//            this.address = builder.address;
//            this.balance = builder.balance;
//            this.firstName = builder.firstName;
//            this.lastName = builder.lastName;
//        }
//
//        @Override
//        public String getFirstName() {
//            return this.firstName;
//        }
//
//        @Override
//        public String getLastName() {
//            return this.lastName;
//        }
//
//        @Override
//        public String getPhoneNumber() {
//            return this.phoneNumber;
//        }
//
//        @Override
//        public String getAddress() {
//            return this.address;
//        }
//
//		
//		@Override
//		public String toString() {
//			StringBuilder builder = new StringBuilder();
//			builder.append("DefaultUser [userName=");
//			builder.append(userName);
//			builder.append(", password=");
//			builder.append(password);
//			builder.append(", phoneNumber=");
//			builder.append(phoneNumber);
//			builder.append(", address=");
//			builder.append(address);
//			builder.append(", balance=");
//			builder.append(balance);
//			builder.append(", firstName=");
//			builder.append(firstName);
//			builder.append(", lastName=");
//			builder.append(lastName);
//			builder.append("]");
//			return builder.toString();
//		}
//
//		@Override
//		public Integer getBalance() {
//			return this.balance;
//		}
//
//		@Override
//		public String getUserName() {
//			return this.userName;
//		}
//
//		@Override
//		public String getPassword() {
//			return this.password;
//		}
//		
//    }
//
//    //inner class
//    class Builder extends DefaultUser {
//
//        public Builder(String firstName, String lastName) {
//            super(firstName, lastName);
//        }
//
//        public Builder phone(String phone) {
//            this.phoneNumber = phone;
//            return this;
//        }
//
//        public Builder address(String address) {
//            this.address = address;
//            return this;
//        }
//
//        public Builder firstName(String firstName) {
//            this.firstName = firstName;
//            return this;
//        }
//        
//        public Builder lastName(String lastName) {
//            this.lastName = lastName;
//            return this;
//        }
//       
//        public Builder balance(int balance) {
//            this.balance =+ balance;
//            return this;
//        }
//
//        public User_Old build() {
//            return new DefaultUser(this);
//        }
//    }
//
//}
