package com.example.moneymoves.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moneymoves.Database.Daos.BudgetTemplateDao;
import com.example.moneymoves.Database.Daos.IDao;
import com.example.moneymoves.Database.Daos.IncomeDao;
import com.example.moneymoves.Database.Daos.MonthlyRecordDao;
import com.example.moneymoves.Database.Daos.MonthlySpentDao;
import com.example.moneymoves.Database.Entities.BudgetTemplate;
import com.example.moneymoves.Database.Entities.IEntity;
import com.example.moneymoves.Database.Entities.Income;
import com.example.moneymoves.Database.Entities.MonthlyRecord;
import com.example.moneymoves.Database.Entities.MonthlySpent;
import com.example.moneymoves.Database.POJOs.NoteAmount;

import java.util.List;

public class MoneyRepository
{
	private BudgetTemplateDao budgetTemplateDao;
	private IncomeDao incomeDao;
	private MonthlyRecordDao monthlyRecordDao;
	private MonthlySpentDao monthlySpentDao;

	private LiveData<List<BudgetTemplate>> allBudgets;
	private LiveData<Double> sumBudgets;
	private LiveData<Double> allIncome;
	private LiveData<List<MonthlyRecord>> allMonthlyRecords;
	private LiveData<List<MonthlySpent>> allMonthlySpent;

	public MoneyRepository(Application application)
	{
		MoneyDatabase database = MoneyDatabase.getInstance(application);

		budgetTemplateDao = database.budgetTemplateDao();
		incomeDao = database.incomeDao();
		monthlyRecordDao = database.monthlyRecordDao();
		monthlySpentDao = database.monthlySpentDao();


		allBudgets = budgetTemplateDao.getAllBudgets();
		allIncome = incomeDao.getIncome();
		allMonthlyRecords = monthlyRecordDao.getMonthlyRecords();
		allMonthlySpent = monthlySpentDao.getMonthlySpent();
		sumBudgets = budgetTemplateDao.sumBudgets();
	}

	public void dummyInsert(MonthlySpent monthlySpent)
	{
		new InsertAsyncTask(monthlySpentDao).execute(monthlySpent);
	}

	public LiveData<List<NoteAmount>> getAllCategoryAmount(String cat)
	{
		return monthlySpentDao.getAllInCategory(cat);
	}

	public LiveData<Double> getSumSpent()
	{
		return monthlySpentDao.getSumSpent();
	}

	public LiveData<List<MonthlySpent>> getAllMonthlySpentFromCat(String cat)
	{
		return monthlySpentDao.getMonthlySpentFromCat(cat);
	}

	public LiveData<Double> getCategoryBudget(String cat)
	{
		return budgetTemplateDao.getCategoryBudget(cat);
	}

	public LiveData<List<BudgetTemplate>> getAllBudgets()
	{
		return allBudgets;
	}

	//if we have multiple incomes, add query in dao to sum the incomes, then new method in repo, then call it in viewModel.
	public LiveData<Double> getAllIncome()
	{
		return allIncome;
	}

	public LiveData<Double> sumBudgets() {return sumBudgets;}

	public LiveData<List<MonthlyRecord>> getAllMonthlyRecords()
	{
		return allMonthlyRecords;
	}

	public LiveData<List<MonthlySpent>> getAllMonthlySpent()
	{
		return allMonthlySpent;
	}

	public LiveData<Double> getSumAmountOfCategory(String cat)
	{
		return monthlySpentDao.getSumAmountOfCategory(cat);
	}

	public void insertBudget(BudgetTemplate budgetTemplate)
	{
		new InsertAsyncTask(budgetTemplateDao).execute(budgetTemplate);
	}

	public void insertIncome(Income income)
	{
		new InsertAsyncTask(incomeDao).execute(income);
		System.out.println("AFTER INSERT IN REPO :: " + incomeDao.getIncome().getValue());
	}

	public void insertMonthlyRecord(MonthlyRecord monthlyRecord)
	{
		new InsertAsyncTask(monthlyRecordDao).execute(monthlyRecord);
	}

	public void insertMonthlySpent(MonthlySpent monthlySpent)
	{
		new InsertAsyncTask(monthlySpentDao).execute(monthlySpent);
	}

	public void updateBudget(BudgetTemplate budgetTemplate)
	{
		new UpdateAsyncTask(budgetTemplateDao).execute(budgetTemplate);
	}

	public void updateIncome(Income income)
	{
		new UpdateAsyncTask(incomeDao).execute(income);
	}

	public void updateMonthlyRecord(MonthlyRecord monthlyRecord)
	{
		new UpdateAsyncTask(monthlyRecordDao).execute(monthlyRecord);
	}

	public void updateMonthlySpent(MonthlySpent monthlySpent)
	{
		new UpdateAsyncTask(monthlySpentDao).execute(monthlySpent);
	}

	public void deleteBudget(BudgetTemplate budgetTemplate)
	{
		new DeleteAsyncTask(budgetTemplateDao).execute(budgetTemplate);
	}

	public void deleteIncome(Income income)
	{
		new DeleteAsyncTask(incomeDao).execute(income);
	}

	public void deleteMonthlyRecord(MonthlyRecord monthlyRecord)
	{
		new DeleteAsyncTask(monthlyRecordDao).execute(monthlyRecord);
	}

	public void deleteMonthlySpent(MonthlySpent monthlySpent)
	{
		new DeleteAsyncTask(monthlySpentDao).execute(monthlySpent);
	}

	public void deleteAllBudgets()
	{
		new DeleteAllBudgetsAsyncTask(budgetTemplateDao).execute();
	}

	private static class InsertAsyncTask extends AsyncTask<IEntity, Void, Void>
	{
		private IDao dao;

		InsertAsyncTask(IDao dao)
		{
			this.dao = dao;
		}

		@Override protected Void doInBackground(IEntity... iEntities)
		{
			dao.insert(iEntities[0]);
			return null;
		}

	}

	private static class UpdateAsyncTask extends AsyncTask<IEntity, Void, Void>
	{
		private IDao dao;

		UpdateAsyncTask(IDao dao)
		{
			this.dao = dao;
		}

		@Override protected Void doInBackground(IEntity... iEntities)
		{
			dao.update(iEntities[0]);
			return null;
		}
	}

	private static class DeleteAsyncTask extends AsyncTask<IEntity, Void, Void>
	{
		private IDao dao;

		DeleteAsyncTask(IDao dao)
		{
			this.dao = dao;
		}

		@Override protected Void doInBackground(IEntity... iEntities)
		{
			dao.delete(iEntities[0]);
			return null;
		}
	}

	private static class DeleteAllBudgetsAsyncTask extends AsyncTask<Void, Void, Void>
	{
		private BudgetTemplateDao budgetTemplateDao;

		public DeleteAllBudgetsAsyncTask(BudgetTemplateDao budgetTemplateDao)
		{
			this.budgetTemplateDao = budgetTemplateDao;
		}

		@Override protected Void doInBackground(Void... voids)
		{
			budgetTemplateDao.deleteAllBudgets();
			return null;
		}
	}

}
