package techplex.core.recipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import techplex.core.util.TPOreUtil;
import techplex.core.util.TPStacksUtil;

public class AlloyFurnaceRecipes {
	private Map<ItemStack, Float>							experienceList	= Maps.newHashMap();
	private Map<ItemStack, Integer>							stack1Size		= Maps.newHashMap();
	private Map<ItemStack, Integer>							stack2Size		= Maps.newHashMap();

	public AlloyFurnaceRecipes() {
		//Stones
		//this.addRecipe(new ItemStack(Blocks.cobblestone, 4, 0), new ItemStack(Blocks.quartz_block, 1, 0), new ItemStack(Blocks.stone, 4, 3), 0.3F);

		//Minerals
		//this.addDictionaryRecipes("ingotTin", 3, "ingotCopper", 3, new ItemStack(MItems.bronze_ingot), 1F);

		//Misc
		//this.addRecipe(new ItemStack(MItems.dough, 1), new ItemStack(Items.bowl, 1), new ItemStack(MItems.bread_bowl, 1), 0.2F);
	}

	public class AlloyFurnaceRecipe {
		private final ItemStack baseItem;
		private final ItemStack first;
		private final ItemStack result;

		public AlloyFurnaceRecipe(final ItemStack first, final ItemStack baseItem, final ItemStack result) {
			this.first = first;
			this.baseItem = baseItem;
			this.result = result;
		}

		public ItemStack getCraftingResult() {
			return this.result.copy();
		}

		public ItemStack[] getIngredients() {
			return new ItemStack[] { this.first, this.baseItem };
		}

		@SuppressWarnings("unused")
		public boolean matches(final ItemStack first, final ItemStack second) {
			if (first.isItemEqual(second)) { return false; }

			if (this.uses(first) && this.uses(second)) { return true; }

			if (this.uses(first) && (second == null)) { return true; }

			if (this.uses(second) && (first == null)) { return true; }

			return this.matchesOreDict(first, second);
		}

		private boolean matchesOreDict(final ItemStack first, final ItemStack second) {
			if (TPOreUtil.matchesOreDict(first,this.first) && TPOreUtil.matchesOreDict(second, this.baseItem)) { return true; }

			if (TPOreUtil.matchesOreDict(first,this.baseItem) && TPOreUtil.matchesOreDict(second, this.first)) { return true; }

			if (TPOreUtil.matchesOreDict(first, this.first) && (second == null)) { return true; }

			if (TPOreUtil.matchesOreDict(second, this.first) && (first == null)) { return true; }

			return false;
		}

		public boolean uses(final ItemStack ingredient) {
			if (ingredient == null) { return false; }

			if ((this.first != null) && this.first.isItemEqual(ingredient)) {
				return true;
			}
			else if ((this.baseItem != null) && this.baseItem.isItemEqual(ingredient)) { return true; }

			return false;
		}
	}

	private static AlloyFurnaceRecipes instance = new AlloyFurnaceRecipes();

	public static AlloyFurnaceRecipes getInstance()
	{
		return AlloyFurnaceRecipes.instance;
	}

	private final ArrayList<AlloyFurnaceRecipe> recipes = new ArrayList<AlloyFurnaceRecipe>();

	public void addRecipe(ItemStack itemStack, ItemStack otherItemStack, ItemStack output, float experience)
	{
		this.recipes.add(new AlloyFurnaceRecipe(itemStack, otherItemStack, output));

		this.stack1Size.put(itemStack, itemStack.stackSize);
		this.stack2Size.put(otherItemStack, otherItemStack.stackSize);

		this.stack1Size.put(otherItemStack, otherItemStack.stackSize);
		this.stack2Size.put(itemStack, itemStack.stackSize);

		this.experienceList.put(output, Float.valueOf(experience));
	}

	public ItemStack getAlloyResult(ItemStack itemStack, ItemStack otherItemStack)
	{
		for (int j = 0; j < this.recipes.size(); ++j)
		{
			AlloyFurnaceRecipe irecipe = this.recipes.get(j);

			if (irecipe.matches(itemStack, otherItemStack)) 
			{ 
				if((TPStacksUtil.equals(itemStack, irecipe.first) && TPStacksUtil.equals(otherItemStack, irecipe.baseItem)) || (TPStacksUtil.equals(otherItemStack, irecipe.first) && TPStacksUtil.equals(itemStack, irecipe.baseItem)))
				{
					return irecipe.getCraftingResult(); 
				}
			}
		}

		return null;
	}

	public boolean hasUsage(ItemStack itemStack)
	{
		for (int j = 0; j < this.recipes.size(); ++j)
		{
			AlloyFurnaceRecipe irecipe = this.recipes.get(j);

			if (irecipe.uses(itemStack)) 
			{ 
				return true; 
			}
		}
		return false;
	}

	public ArrayList<AlloyFurnaceRecipe> getRecipesFor(ItemStack output)
	{
		ArrayList<AlloyFurnaceRecipe> list = new ArrayList<AlloyFurnaceRecipe>();

		for (AlloyFurnaceRecipe alloyRecipe : this.recipes)
		{
			if (alloyRecipe.result.isItemEqual(output) && alloyRecipe.result.getTagCompound() == output.getTagCompound())
			{
				list.add(alloyRecipe);
			}
		}
		return list;
	}

	public ArrayList<AlloyFurnaceRecipe> getRecipesUsing(ItemStack ingredient)
	{
		ArrayList<AlloyFurnaceRecipe> list = new ArrayList<AlloyFurnaceRecipe>();

		for (AlloyFurnaceRecipe alloyRecipe : this.recipes)
		{
			if (alloyRecipe.uses(ingredient))
			{
				list.add(alloyRecipe);
				continue;
			}

			if (TPOreUtil.matchesOreDict(ingredient, alloyRecipe.first))
			{
				list.add(new AlloyFurnaceRecipe(ingredient, alloyRecipe.baseItem.copy(), alloyRecipe.result.copy()));
				continue;
			}

			if (TPOreUtil.matchesOreDict(ingredient, alloyRecipe.baseItem))
			{
				list.add(new AlloyFurnaceRecipe(alloyRecipe.first.copy(), ingredient, alloyRecipe.result.copy()));
			}
		}
		return list;
	}

	public float getSmeltingExperience(ItemStack stack)
	{
		float ret = stack.getItem().getSmeltingExperience(stack);
		if (ret != -1)
			return ret;

		Iterator iterator = this.experienceList.entrySet().iterator();
		Entry entry;

		do
		{
			if (!iterator.hasNext())
				return 0.0F;

			entry = (Entry) iterator.next();
		}
		while (!TPStacksUtil.equals(stack, (ItemStack) entry.getKey()));

		return ((Float) entry.getValue()).floatValue();
	}

	public int getInputSize(ItemStack stack)
	{
		Iterator iterator = this.stack1Size.entrySet().iterator();
		Entry entry;

		do
		{
			if (!iterator.hasNext())
				return 0;

			entry = (Entry) iterator.next();
		}
		while (!TPStacksUtil.equals(stack, (ItemStack) entry.getKey()));

		return (Integer) entry.getValue();
	}

	public int getInput2Size(ItemStack stack)
	{
		Iterator iterator = this.stack2Size.entrySet().iterator();
		Entry entry;

		do
		{
			if (!iterator.hasNext())
				return 0;

			entry = (Entry) iterator.next();
		}
		while (!TPStacksUtil.equals(stack, (ItemStack) entry.getKey()));

		return (Integer) entry.getValue();
	}

	public void addDictionaryRecipes(String name, int amount, String otherName, int amount2, ItemStack output, Float exp)
	{
		ItemStack[] set1 = OreDictionary.getOres(name).toArray(new ItemStack[OreDictionary.getOres(name).size()]);
		ItemStack[] set2 = OreDictionary.getOres(otherName).toArray(new ItemStack[OreDictionary.getOres(otherName).size()]);

		for(int i = 0; i < set1.length; i++)
		{
			for(int i2 = 0; i2 < set2.length; i2++)
			{
				this.addRecipe(new ItemStack(set1[i].getItem(), amount), new ItemStack(set2[i2].getItem(), amount2), output, exp);
			}
		}	
	}
}
