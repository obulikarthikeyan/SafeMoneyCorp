package edu.asu.safemoney.service.impln;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antlr.collections.List;
import edu.asu.safemoney.dao.ManageExternalUserAccountDAO;
import edu.asu.safemoney.dto.TransactionDTO;
import edu.asu.safemoney.dto.UserDTO;
import edu.asu.safemoney.helper.ExternalUserHelper;
import edu.asu.safemoney.model.AccountModel;
import edu.asu.safemoney.model.TransactionModel;
import edu.asu.safemoney.model.UserModel;
import edu.asu.safemoney.service.ManageExternalUserAccountService;

@Service
public class ManageExternalUserAccountServiceImpl implements
		ManageExternalUserAccountService {

	@Autowired
	ManageExternalUserAccountDAO manageExternalUserAccountDAO;

	private AccountModel accntModel;

	public AccountModel getAccntModel() {
		return accntModel;
	}

	public void setAccntModel(AccountModel accntModel) {
		this.accntModel = accntModel;
	}

	@Override
	@Transactional
	public void updateUser(UserModel userModel) {
		manageExternalUserAccountDAO.updateUser(userModel);
	}

	@Override
	@Transactional
	public void deleteUser(String nameOfUser) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public UserDTO displayUserAccount(int memberId) {
		UserDTO userDTO = manageExternalUserAccountDAO
				.displayUserAccountDAO(memberId);
		return userDTO;
	}

	@Transactional
	public AccountModel getAccountDetails(int memberId) {
		// TODO Auto-generated method stub
		AccountModel accountModel = manageExternalUserAccountDAO
				.getAccountDetails(memberId);
		this.setAccntModel(accountModel);
		return accountModel;
	}

	@Transactional
	public double getAccountBalance(int memberId) {
		AccountModel accountModel = manageExternalUserAccountDAO
				.getAccountDetails(memberId);
		if (accountModel != null) {
			return accountModel.getAmount();
		} else {
			return -1;
		}
	}

	@Transactional
	@Override
	public String makeDebitTransaction(int memberID, double amount) {
		// TODO Auto-generated method stub
		if (amount > 2000) {

		} else {
			double balance = getAccountBalance(memberID);
			if (balance > amount) {
				balance -= amount;
				boolean isSuccess = manageExternalUserAccountDAO
						.updateAccountBalance(memberID, balance);
				if (isSuccess) {
					AccountModel accountModel = this.getAccntModel();
					TransactionDTO txnDTO = new TransactionDTO();
					txnDTO.setAmount(amount);
					txnDTO.setDate(new Date());
					txnDTO.setFromAccount(accountModel.getAccountNo());
					txnDTO.setToAccount(accountModel.getAccountNo());
					txnDTO.setIsAuthorized(true);// boolean
					txnDTO.setIsCritical(false);
					txnDTO.setMemberId(displayUserAccount(memberID));// input
																		// UserDTO
					txnDTO.setStatus("APPROVED");//
					txnDTO.setTransactionId(ExternalUserHelper.generateRandomNumber());// long
					txnDTO.setTransactionType("Debit");

					boolean isTxnCreated = manageExternalUserAccountDAO
							.createTransaction(txnDTO);
					if (isTxnCreated) {
						return "success";
					}
				} else {
					return "failure";
				}
			}
			else
			{
				return "NOFUND";
			}
		}
		return "failure";
	}

	@Override
	public boolean makeCreditTransaction(int memberID, double amount) {
		// TODO Auto-generated method stub
		return false;

	}

}
